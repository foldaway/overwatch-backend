const ExtractTextPlugin = require('extract-text-webpack-plugin')
const { env } = require('../configuration.js')

module.exports = {
  test: /\.(scss|sass|css)$/i,
  use: [
    { loader: 'style-loader' },
    { loader: 'css-loader', options: { minimize: env.NODE_ENV === 'production' } },
    { loader: 'postcss-loader', options: { sourceMap: true } },
    'resolve-url-loader',
    { loader: 'sass-loader', options: { sourceMap: true } }
  ]
}
