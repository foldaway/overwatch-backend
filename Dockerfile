FROM debian:stretch-slim

WORKDIR /app
ADD . /app

RUN buildDeps=" \
  build-essential \
  curl \
  zlib1g-dev \
  ruby-dev"; \
  apt-get update \
  && apt-get install -y $buildDeps sudo \
  && curl -sL https://deb.nodesource.com/setup_8.x | sudo -E bash - \
  && curl -sS https://dl.yarnpkg.com/debian/pubkey.gpg | sudo apt-key add - \
  && echo "deb https://dl.yarnpkg.com/debian/ stable main" | sudo tee /etc/apt/sources.list.d/yarn.list \
  && apt-get update \
  && apt-get install -y --no-install-recommends nodejs yarn ruby libpq-dev \
  && gem install bundler \
  && bundle install --without development test \
  && bundle clean --force \
  && yarn install \
  && yarn cache clean \
  && bundle exec rake NODE_ENV=production RAILS_ENV=production assets:precompile \
  && rm -rf node_modules \
  && rm -rf /usr/local/share/.cache/yarn \
  && apt-get purge -y --auto-remove $buildDeps nodejs yarn \
  && rm -rf /var/lib/apt

CMD ["bundle", "exec", "rails", "server", "-p", "5000"]
