/**
 * Asynchronously loads the component for HomePage
 */

import { lazyLoad } from 'utils/loadable';

export const Carts = lazyLoad(
  () => import('./index'),
  module => module.Carts,
);
