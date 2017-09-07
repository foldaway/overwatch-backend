import React from 'react';
import PropTypes from 'prop-types';
import styles from './styles.scss';

const getStarCount = (level) => {
  const hundredCount = Math.floor(level / 100);
  if (level >= 1801) {
    // Plat
    return hundredCount - 18;
  } else if (level >= 1201) {
    // Gold
    return hundredCount - 12;
  } else if (level >= 601) {
    // Silver
    return hundredCount - 6;
  }
  return hundredCount;
};

const getLevelStyle = (level) => {
  if (level >= 1801) {
    // Plat
    return styles.platinum;
  } else if (level >= 1201) {
    // Gold
    return styles.gold;
  } else if (level >= 601) {
    // Silver
    return styles.silver;
  }
  return styles.bronze;
};

const LevelDisplay = ({ level }) => {
  const className = [styles.container, getLevelStyle(level)].join(' ');
  const stars = '★'.repeat(getStarCount(level));

  return (
    <div className={className}>
      <span className={styles.value}>{level % 100}</span>
      <div className={styles.stars}>
        <span>{stars}</span>
      </div>
    </div>
  );
};

LevelDisplay.propTypes = {
  level: PropTypes.number.isRequired,
};

export default LevelDisplay;
