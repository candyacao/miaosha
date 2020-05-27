import React from 'react';
import { Helmet } from 'react-helmet-async';
import axios from 'axios';

function ProductsItem(props) {
  let pid = props.product.id;
  let url = '/product/' + pid;
  let name = props.product.name;
  let title = props.product.title;
  let detail = props.product.detail;
  let price = props.product.price;
  let stock = props.product.stock;

  return (
    <li key={pid}>
      <a href={url}>{name}</a>
      <header>{title}</header>
      <section>
        <header>{detail}</header>
        <section>价格: {price}</section>
        <section>库存: {stock}</section>
      </section>
    </li>
  );
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

export function Products() {
  return (
    <>
      <Helmet>
        <title>Products</title>
        <meta name="description" content="A Boilerplate application homepage" />
      </Helmet>
      <span>Products container</span>
      <ProductsItems></ProductsItems>
    </>
  );
}
