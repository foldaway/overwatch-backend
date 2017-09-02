import React from 'react';
import { Link } from 'react-router-dom';
import styles from './styles.scss';

const Header = () => (
  <Link to="/" style={{ textDecoration: 'none' }} >
    <div className={styles.header}>
      <h1>Overwatch</h1>
      <h1 className={styles.orange}>Dashboard</h1>
    </div>
  </Link>
);

export default Header;
