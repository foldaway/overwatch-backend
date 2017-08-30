import React, { Component } from 'react';
import axios from 'axios';
import Card from '../Card';

class PlayerDetail extends Component {
  constructor(props) {
    super(props);
    this.state = {
      player: null,
    };
  }
  componentDidMount() {
    const { playerId } = this.props.match.params;
    axios.get(`/api/v1/players/${playerId}`)
      .then(res => res.data)
      .then(player => axios.get(`/api/v1/players/${player.id}/data`)
        .then(res => res.data)
        .then(datas => Object.assign(player, { datas })),
      )
      .then(player => this.setState({ player }))
      .catch(console.error);
  }

  render() {
    if (this.state.player !== null) {
      const { id, battle_tag, player_icon, datas } = this.state.player;
      return (
        <div>
          <Card playerId={id} battleTag={battle_tag} playerIcon={player_icon} data={Array.isArray(datas) ? datas[0] : null} />
        </div>
      );
    }

    return (
      <span>Loading...</span>
    );
  }
}

export default PlayerDetail;
