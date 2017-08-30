import React from 'react';
import { Switch, Route } from 'react-router-dom';
import CardList from '../CardList';
import PlayerDetail from '../PlayerDetail';

const Main = () => (
  <main>
    <Switch>
      <Route exact path="/" component={CardList} />
      <Route path="/player" component={PlayerDetail} />
    </Switch>
  </main>
);

export default Main;
