import React from 'react';
import axios from 'axios';
import Card from '../Card';

class CardList extends React.Component {
  constructor(props) {
    super(props);

    this.state = {
      players: [],
    };
  }

  componentDidMount() {
    axios.get('/api/v1/players')
      .then(res => res.data)
      .then(players => Promise.all(
        players.map(player => axios.get(`/api/v1/players/${player.id}/data`)
          .then(res => res.data)
          .then(datas => Object.assign(player, { datas })),
        ),
      ).then(players => this.setState({ players })),
      );
  }

  render() {
    console.log(this.state.cards);
    return (
      <ul>
        {this.state.players.map(({ id, battle_tag, player_icon, datas }) =>
          <Card key={id} battleTag={battle_tag} playerIcon={player_icon} seasonRating={datas[0].sr} />,
        )}
      </ul>
    );
  }
}

export default CardList;
