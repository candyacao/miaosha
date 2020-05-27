/**
 * Asynchronously loads the component for HomePage
 */

import { lazyLoad } from 'utils/loadable';

export const Products = lazyLoad(
  () => import('./index'),
  module => module.Products,
);
