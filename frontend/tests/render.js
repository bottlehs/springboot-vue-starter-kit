import { render as r } from '@testing-library/vue'
import { routes } from '../src/router'
import store from '../src/store'

export function render (ui) {
  return r(
    ui,
    {
      routes,
      store
    }
  )
}
