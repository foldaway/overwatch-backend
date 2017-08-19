import React from 'react';
import PropTypes from 'prop-types';
import styles from './styles.scss';

const Card = props => (
  <div className={styles.card}>
    <div className={styles.mask} />
    <div className={styles.hero}>
      <img src={props.mainCompImg} alt="" />
    </div>
    <div className={styles.bottomPanel}>
      <img className={styles.playerIcon} src={props.playerIcon} alt="" />
      <div className={styles.details}>
        <span>{props.battleTag}</span>
        <span>SR: {props.seasonRating}</span>
      </div>
    </div>
  </div>
);

Card.defaultProps = {
  battleTag: 'N/A',
  playerIcon: '',
  mainCompImg: '',
  seasonRating: -1,
};

Card.propTypes = {
  battleTag: PropTypes.string,
  playerIcon: PropTypes.string,
  mainCompImg: PropTypes.string,
  seasonRating: PropTypes.number,
};

export default Card;
