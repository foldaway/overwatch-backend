import React from 'react';
import { Link } from 'react-router-dom';
import styles from './styles.scss';

const Header = () => (
  <div className={styles.header}>
    <Link to="/" style={{ textDecoration: 'none' }} className={styles.link}>
      <h1>Overwatch</h1>
      <hr />
    </Link>

  </div>
);

export default Header;
