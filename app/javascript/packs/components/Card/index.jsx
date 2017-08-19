import React from 'react';
import PropTypes from 'prop-types';
import axios from 'axios';
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
      .then(res => res.data)
      .then(hero => this.setState({ mainCompImg: hero.img }))
      .catch(console.error);
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
            {
              this.props.data.sr !== -1 ? (
                <span>SR: {this.props.data.sr}</span>
              ) : (
                <span>No SR</span>
              )
            }
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
  },
};

Card.propTypes = {
  battleTag: PropTypes.string,
  playerIcon: PropTypes.string,
  data: PropTypes.shape({
    mainQP_id: PropTypes.number,
    mainComp_id: PropTypes.number,
    sr: PropTypes.number,
  }),
};

export default Card;
