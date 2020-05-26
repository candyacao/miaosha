import React from 'react';
import { Helmet } from 'react-helmet-async';
import axios from 'axios';

class MeProfile extends React.Component<any, any> {
  constructor(props) {
    super(props);
    this.state = {
      me: {},
    };
  }
  getData() {
    axios.get('/me').then(response => {
      console.log(response);
      this.setState({ me: response.data });
    });
  }
  componentWillMount() {
    this.getData();
  }
  render() {
    return <p>hello {this.state.me?.nickName || 'any'}</p>;
  }
}

export function HomePage() {
  return (
    <>
      <Helmet>
        <title>Home Page</title>
        <meta name="description" content="A Boilerplate application homepage" />
      </Helmet>
      <span>HomePage container</span>
      <MeProfile></MeProfile>
    </>
  );
}
