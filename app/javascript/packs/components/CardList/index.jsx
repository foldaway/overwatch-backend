import React from 'react';
import axios from 'axios';
import Card from '../Card';
import styles from './styles.scss';

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
      ))
      .then(players => this.setState({ players }))
      .catch(console.error);
  }

  render() {
    return (
      <ul className={styles.list}>
        {this.state.players.map(({ id, battle_tag, player_icon, datas }) => (
          <li key={id}>
            <Card battleTag={battle_tag} playerIcon={player_icon} data={Array.isArray(datas) ? datas[0] : null} />
          </li>
        ))}
      </ul>
    );
  }
}

export default CardList;
