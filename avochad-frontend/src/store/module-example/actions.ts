import { ActionTree } from 'vuex'
import { StateInterface } from '../index'
import { MainStateInterface } from './state'
import { CURRENT_AUTHORIZED_USER } from './mutations'

const actions: ActionTree<MainStateInterface, StateInterface> = {
  [CURRENT_AUTHORIZED_USER] (context, authorizedUser: object) {
    context.commit(CURRENT_AUTHORIZED_USER, authorizedUser)
  }
}

export default actions
