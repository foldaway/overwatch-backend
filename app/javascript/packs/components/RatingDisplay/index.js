import React from 'react';
import PropTypes from 'prop-types';
import styles from './styles.scss';

const getRatingStyle = (rating) => {
  if (rating >= 4000) {
    return styles.grandMaster;
  } else if (rating >= 3500) {
    return styles.master;
  } else if (rating >= 3000) {
    return styles.diamond;
  } else if (rating >= 2500) {
    return styles.platinum;
  } else if (rating >= 2000) {
    return styles.gold;
  } else if (rating >= 1500) {
    return styles.silver;
  }
  return styles.bronze;
};

const RatingDisplay = ({ rating }) => {
  if (rating === -1) {
    return null;
  }

  const className = [styles.rating, getRatingStyle(rating)].join(' ');

  return (
    <span className={className}>{rating}</span>
  );
};

RatingDisplay.propTypes = {
  rating: PropTypes.number.isRequired,
};

export default RatingDisplay;
