import React from 'react';
import axios from 'axios';
import PropTypes from 'prop-types';
import { Link } from 'react-router-dom';

import LevelDisplay from '../LevelDisplay';
import RatingDisplay from '../RatingDisplay';
import styles from './styles.scss';

class Card extends React.Component {
  constructor(props) {
    super(props);

    this.state = {
      mainCompImg: '',
    };
  }

  componentDidMount() {
    axios.get(`/api/v1/heroes/${this.props.data.sr !== -1 ? this.props.data.mainComp_id : this.props.data.mainQP_id}`)
      .then((res) => res.data)
      .then((hero) => this.setState({ mainCompImg: hero.img }))
      .catch(console.error);
  }

  renderUserInfo() {
    const { playerIcon, battleTag, data } = this.props;

    return (
      <div className={styles.bottomPanel}>
        <img className={styles.playerIcon} src={playerIcon} alt="" />
        <span className={styles.battleTag}>{battleTag}</span>
        <div className={styles.tags}>
          <LevelDisplay level={data.level} />
          <RatingDisplay rating={data.sr} />
        </div>
      </div>
    );
  }

  render() {
    return (
      <div className={styles.card}>
        <Link to={`/player/${this.props.playerId}`} className={styles.link}>
          <div className={styles.mask} />
          <img className={styles.hero} src={this.state.mainCompImg} alt="" />
          {this.renderUserInfo()}
        </Link>
      </div>
    );
  }
}

Card.defaultProps = {
  playerId: -1,
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
  playerId: PropTypes.number,
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
