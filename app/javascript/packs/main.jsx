import 'normalize.css';

import React from 'react';
import ReactDOM from 'react-dom';
import { BrowserRouter } from 'react-router-dom';
import Header from './components/Header';
import Main from './components/Main';

document.addEventListener('DOMContentLoaded', () => {
  ReactDOM.render(
    (
      <BrowserRouter>
        <div>
          <Header />
          <Main />
        </div>
      </BrowserRouter>
    ),
    document.body.appendChild(document.createElement('div')),
  );
});
