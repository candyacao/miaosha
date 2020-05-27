import React from 'react';
import { Helmet } from 'react-helmet-async';
import axios from 'axios';
import querystring from 'querystring';

class ProductsItem extends React.Component<any, any> {
  constructor(props) {
    super(props);
    this.state = {
      productID: this.props.product.id,
      quantity: 1,
    };
    this.handleSubmit = this.handleSubmit.bind(this);
  }
  handleSubmit(event) {
    console.log(this.state);
    axios
      .post('/addCart', querystring.stringify(this.state))
      .then(response => {
        console.log(response);
      })
      .catch(error => {
        console.log(error);
      });
    event.preventDefault();
  }

  render() {
    let product = this.props.product;
    let pid = product.id;
    let url = '/product/' + pid;
    let name = product.name;
    let title = product.title;
    let detail = product.detail;
    let price = product.price;
    let stock = product.stock;

    return (
      <li key={pid}>
        <a href={url}>{name}</a>
        <header>{title}</header>
        <section>
          <header>{detail}</header>
          <section>价格: {price}</section>
          <section>库存: {stock}</section>
          <button onClick={this.handleSubmit}>加入购物车</button>
        </section>
      </li>
    );
  }
}

class ProductsItems extends React.Component<any, any> {
  constructor(props) {
    super(props);
    this.state = {
      products: [],
    };
  }
  getData() {
    axios.get('/getProducts').then(response => {
      console.log(response);
      this.setState({ products: response.data?.data });
    });
  }
  componentWillMount() {
    this.getData();
  }
  render() {
    const products: Array<any> = this.state.products;
    console.log('products ', products);
    const listItems = products.map(product => (
      <ProductsItem product={product} key={product.id}></ProductsItem>
    ));
    return <ul>{listItems}</ul>;
  }
}

export function Products(props) {
  return (
    <>
      <Helmet>
        <title>Products</title>
        <meta name="description" content="A Boilerplate application homepage" />
      </Helmet>
      <span>Products container</span>
      <ProductsItems {...props}></ProductsItems>
    </>
  );
}
