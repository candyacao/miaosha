import React from 'react';
import { Helmet } from 'react-helmet-async';
import axios from 'axios';
import querystring from 'querystring';

class SignUpForm extends React.Component<any, any> {
  constructor(props) {
    super(props);
    this.state = {
      nickName: '',
      username: '',
      password: '',
      userSex: '0',
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
      .post('/register', querystring.stringify(this.state))
      .then(response => {
        console.log(response);
      })
      .catch(error => {
        console.log(error);
      });
    event.preventDefault();
  }

  render() {
    return (
      <form onSubmit={this.handleSubmit}>
        <label>
          昵称:
          <input
            type="text"
            value={this.state.nickName}
            name="nickName"
            onChange={this.handleChange}
          />
        </label>
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
        <label>
          性别:
          <select
            value={this.state.userSex}
            name="userSex"
            onChange={this.handleChange}
          >
            <option value="0">男</option>
            <option value="1">女</option>
          </select>
        </label>
        <input type="submit" value="signup" />
      </form>
    );
  }
}

export function SignUp() {
  return (
    <>
      <Helmet>
        <title>SignUp Page</title>
      </Helmet>
      <span>SignUp container</span>
      <SignUpForm></SignUpForm>
    </>
  );
}
