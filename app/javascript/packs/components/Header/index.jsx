import React from 'react';
import styles from './styles.scss';

const Header = () => (
  <div className={styles.header}>
    <h1>Overwatch</h1>
    <h1 className={styles.orange}>Dashboard</h1>
  </div>
);

export default Header;
