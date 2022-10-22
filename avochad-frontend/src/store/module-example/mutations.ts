import { MutationTree } from 'vuex'
import { MainStateInterface } from './state'

export const CURRENT_AUTHORIZED_USER = 'currentAuthorizedUser'

const mutation: MutationTree<MainStateInterface> = {

  [CURRENT_AUTHORIZED_USER] (state, currentAuthorizedUser: object) {
    state.authorizedUser = currentAuthorizedUser
  }

}

export default mutation
