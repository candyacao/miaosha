import React from 'react';
import { Helmet } from 'react-helmet-async';
import axios from 'axios';

class CartsItem extends React.Component<any, any> {
  constructor(props) {
    super(props);
    this.state = {
      product: {},
    };
    this.handleSubmit = this.handleSubmit.bind(this);
  }
  getData() {
    axios
      .get('/getProductByID?id=' + this.props.cart?.productID)
      .then(response => {
        console.log(response);
        this.setState({ product: response.data?.data || [] });
      });
  }
  componentWillMount() {
    this.getData();
  }
  handleSubmit(event) {
    console.log(this.state);
    event.preventDefault();
  }

  render() {
    let cart = this.props.cart;
    let id = cart.id;
    let pid = cart.productID;
    let product_url = '/product/' + pid;
    let price = cart.price;
    let quantity = cart.quantity;

    return (
      <li key={id}>
        <a href={product_url}>{this.state.product?.name}</a>
        <section>
          <section>价格: {price}</section>
          <section>数量: {quantity}</section>
        </section>
      </li>
    );
  }
}

class CartsItems extends React.Component<any, any> {
  constructor(props) {
    super(props);
    this.state = {
      carts: [],
    };
  }
  getData() {
    axios.get('/getCarts').then(response => {
      console.log(response);
      this.setState({ carts: response.data?.data || [] });
    });
  }
  componentWillMount() {
    this.getData();
  }
  render() {
    const carts: Array<any> = this.state.carts;
    console.log('carts ', carts);
    const listItems = carts.map(cart => (
      <CartsItem cart={cart} key={cart.id}></CartsItem>
    ));
    return <ul>{listItems}</ul>;
  }
}

export function Carts(props) {
  return (
    <>
      <Helmet>
        <title>Carts</title>
        <meta name="description" content="A Boilerplate application homepage" />
      </Helmet>
      <span>Products container</span>
      <CartsItems {...props}></CartsItems>
    </>
  );
}
