import React from 'react';
import { Helmet } from 'react-helmet-async';
import querystring from 'querystring';
import axios from 'axios';

class SignInForm extends React.Component<any, any> {
  constructor(props) {
    super(props);
    this.state = {
      username: '',
      password: '',
    };
    this.handleChange = this.handleChange.bind(this);
    this.handleSubmit = this.handleSubmit.bind(this);
  }

  handleChange(event) {
    const target = event.target;
    const name = target.name;
    this.setState({
      [name]: target.value,
    });
    console.log(JSON.stringify(this.state));
  }

  handleSubmit(event) {
    console.log(this.state);
    axios
      .post('/login', querystring.stringify(this.state))
      .then(response => {
        console.log(response);
        this.props.history.push('/');
      })
      .catch(error => {
        console.log(error);
      });
    event.preventDefault();
  }

  render() {
    return (
      <form onSubmit={this.handleSubmit}>
        <br />
        <label>
          账号:
          <input
            type="text"
            value={this.state.username}
            name="username"
            onChange={this.handleChange}
          />
        </label>
        <br />
        <label>
          密码:
          <input
            type="text"
            value={this.state.password}
            name="password"
            onChange={this.handleChange}
          />
        </label>
        <br />
        <input type="submit" value="signin" />
      </form>
    );
  }
}

export function SignIn(props) {
  return (
    <>
      <Helmet>
        <title>SignIn Page</title>
        <meta name="description" content="A Boilerplate application homepage" />
      </Helmet>
      <span>SignIn container</span>
      <SignInForm {...props}></SignInForm>
    </>
  );
}
