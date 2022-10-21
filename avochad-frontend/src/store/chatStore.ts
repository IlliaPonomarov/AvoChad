import { defineStore } from 'pinia'
import { loginStore } from "src/store/module-example/authStore"

export const CURRENT_AUTHORIZED_USER = 'authorizedUser'

interface MainState {
  authorizedUser: object
}

export const useMainStore = defineStore('main', {

  state: () => {
    return {
      authorizedUser: {
        email: 'hello',
        password: ''
      },
      chats: [
        {
          id: 1,
          title: 'Razvan Stoenescu',
          avatar: 'https://cdn.quasar.dev/team/razvan_stoenescu.jpeg',
          lastMessage: 'I\'m working on Quasar!',
          time: '15:00',
          sent: true
        },
        {
          id: 2,
          title: 'Dan Popescu',
          avatar: 'https://cdn.quasar.dev/team/dan_popescu.jpg',
          lastMessage: 'I\'m working on Quasar!',
          time: '16:00',
          sent: true
        },
        {
          id: 3,
          title: 'Jeff Galbraith',
          avatar: 'https://cdn.quasar.dev/team/jeff_galbraith.jpg',
          lastMessage: '',
          time: '18:00',
          sent: true
        },
        {
          id: 4,
          title: 'Allan Gaunt',
          avatar: 'https://cdn.quasar.dev/team/allan_gaunt.png',
          lastMessage: 'I\'m working on Quasar!',
          time: '17:00',
          sent: true
        }
      ]
    }
  }

})
export default useMainStore
// function state (): ChatStoreState {
//   return {
//     chats: [
//       {
//         id: 1,
//         title: 'Razvan Stoenescu',
//         avatar: 'https://cdn.quasar.dev/team/razvan_stoenescu.jpeg',
//         lastMessage: 'I\'m working on Quasar!',
//         time: '15:00',
//         sent: true
//       },
//       {
//         id: 2,
//         title: 'Dan Popescu',
//         avatar: 'https://cdn.quasar.dev/team/dan_popescu.jpg',
//         lastMessage: 'I\'m working on Quasar!',
//         time: '16:00',
//         sent: true
//       },
//       {
//         id: 3,
//         title: 'Jeff Galbraith',
//         avatar: 'https://cdn.quasar.dev/team/jeff_galbraith.jpg',
//         lastMessage: '',
//         time: '18:00',
//         sent: true
//       },
//       {
//         id: 4,
//         title: 'Allan Gaunt',
//         avatar: 'https://cdn.quasar.dev/team/allan_gaunt.png',
//         lastMessage: 'I\'m working on Quasar!',
//         time: '17:00',
//         sent: true
//       }
//     ]
//   }
// }
