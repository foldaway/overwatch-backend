import React from 'react';
import PropTypes from 'prop-types';
import axios from 'axios';
import styles from './styles.scss';
import LevelDisplay from '../LevelDisplay';

class Card extends React.Component {
  constructor(props) {
    super(props);

    this.state = {
      mainCompImg: '',
    };
  }

  componentDidMount() {
    axios.get(`/api/v1/heroes/${this.props.data.sr !== -1 ? this.props.data.mainComp_id : this.props.data.mainQP_id}`)
      .then(res => res.data)
      .then(hero => this.setState({ mainCompImg: hero.img }))
      .catch(console.error);
  }

  getSRStyle() {
    const { sr } = this.props.data;
    if (sr >= 4000) {
      return styles.grandMaster;
    } else if (sr >= 3500) {
      return styles.master;
    } else if (sr >= 3000) {
      return styles.diamond;
    } else if (sr >= 2500) {
      return styles.platinum;
    } else if (sr >= 2000) {
      return styles.gold;
    } else if (sr >= 1500) {
      return styles.silver;
    }
    return styles.bronze;
  }

  render() {
    return (
      <div className={styles.card}>
        <div className={styles.mask} />
        <div className={styles.hero}>
          <img src={this.state.mainCompImg} alt="" />
        </div>
        <div className={styles.bottomPanel}>
          <img className={styles.playerIcon} src={this.props.playerIcon} alt="" />
          <div className={styles.details}>
            <span className={styles.battleTag}>{this.props.battleTag}</span>
            <div className={styles.tags}>
              {
                this.props.data.sr !== -1 ? (
                  <span className={[styles.seasonRating, this.getSRStyle()].join(' ')}>{this.props.data.sr}</span>
                ) : (
                  <span className={styles.seasonRating}>?</span>
                )
              }
              <LevelDisplay level={this.props.data.level} />
            </div>
          </div>
        </div>
      </div>
    );
  }
}

Card.defaultProps = {
  battleTag: 'N/A',
  playerIcon: '',
  data: {
    mainQP_id: -1,
    mainComp_id: -1,
    sr: -1,
    level: -1,
  },
};

Card.propTypes = {
  battleTag: PropTypes.string,
  playerIcon: PropTypes.string,
  data: PropTypes.shape({
    mainQP_id: PropTypes.number,
    mainComp_id: PropTypes.number,
    sr: PropTypes.number,
    level: PropTypes.number,
  }),
};

export default Card;
