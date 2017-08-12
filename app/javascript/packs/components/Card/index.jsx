import React from 'react';
import PropTypes from 'prop-types';

const Card = props => (
  <div>
    <img src={props.mainCompImg} alt="" />
    <div>
      <img src={props.playerIcon} alt="" />
      <span>{props.battleTag}</span>
      <span>SR: {props.seasonRating}</span>
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
