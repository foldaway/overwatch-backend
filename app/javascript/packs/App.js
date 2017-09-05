import 'normalize.css';

import React from 'react';
import { BrowserRouter, Switch, Route } from 'react-router-dom';
import Header from './components/Header';
import CardList from './components/CardList';
import PlayerDetail from './components/PlayerDetail';

import './styles.scss';

const App = () => (
  <BrowserRouter>
    <div>
      <Header />
      <Switch>
        <Route
          exact
          path="/"
          render={(props) => <CardList {...props} />}
        />
        <Route
          path="/player/:playerId"
          render={(props) => <PlayerDetail {...props} />}
        />
      </Switch>
    </div>
  </BrowserRouter>
);

export default App;
