import { GetterTree } from 'vuex'
import { StateInterface } from '../index'
import { MainStateInterface } from './state'
import { CURRENT_AUTHORIZED_USER } from './mutations'

const getters: GetterTree<MainStateInterface, StateInterface> = {
  [CURRENT_AUTHORIZED_USER] (state) {
    return state.authorizedUser
  }
}

export default getters
