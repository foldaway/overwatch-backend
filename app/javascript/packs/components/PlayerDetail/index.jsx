import React, { Component } from 'react';
import axios from 'axios';
import { LineChart, XAxis, YAxis, CartesianGrid, Line, Tooltip } from 'recharts';
import Card from '../Card';
import styles from './styles.scss';

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
      const chartData = datas
        .map(data => Object.assign(data, {
          created_at: new Date(data.created_at).toLocaleString(),
        }))
        .reverse();
      return (
        <div className={styles.container}>
          <Card playerId={id} battleTag={battle_tag} playerIcon={player_icon} data={Array.isArray(datas) ? datas[0] : null} />
          <div className={styles.charts}>
            <div>
              <h1>SR</h1>
              <LineChart width={400} height={200} data={chartData} >
                <XAxis dataKey="created_at" />
                <YAxis />
                <Tooltip />
                <CartesianGrid stroke="#f5f5f5" />
                <Line type="monotone" dataKey="sr" stroke="#ff7300" yAxisId={0} />
              </LineChart>
            </div>
            <div>
              <h1>Level</h1>
              <LineChart width={400} height={200} data={chartData} >
                <XAxis dataKey="created_at" />
                <YAxis />
                <Tooltip />
                <CartesianGrid stroke="#f5f5f5" />
                <Line type="monotone" dataKey="level" stroke="#ff7300" yAxisId={0} />
              </LineChart>
            </div>
          </div>
        </div>
      );
    }

    return (
      <span>Loading...</span>
    );
  }
}

export default PlayerDetail;
