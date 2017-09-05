import React from 'react';
import { Link } from 'react-router-dom';
import styles from './styles.scss';

const Header = () => (
  <div className={styles.header}>
    <Link to="/" style={{ textDecoration: 'none' }} className={styles.link}>
      <span>Overwatch</span>
      <hr />
    </Link>

  </div>
);

export default Header;
