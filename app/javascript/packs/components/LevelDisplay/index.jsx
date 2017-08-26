import React from 'react';
import PropTypes from 'prop-types';
import styles from './styles.scss';

class LevelDisplay extends React.Component {
  getStarCount() {
    const { level } = this.props;
    const hundredCount = Math.floor(this.props.level / 100);
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
  }

  getStyle() {
    const { level } = this.props;
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
  }

  render() {
    const { level } = this.props;
    return (
      <div className={[styles.container, this.getStyle()].join(' ')}>
        <span className={styles.value}>{level % 100}</span>
        <span className={styles.stars}>{'★'.repeat(this.getStarCount())}</span>
      </div>
    );
  }
}

LevelDisplay.propTypes = {
  level: PropTypes.number.isRequired,
};

export default LevelDisplay;
