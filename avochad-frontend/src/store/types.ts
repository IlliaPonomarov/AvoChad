export interface AvatarInterface {
  id: number
  path: string
}

export interface UserInterface {
  id: number
  firstName: string
  lastName: string
  avatar: AvatarInterface
}

// eslint-disable-next-line @typescript-eslint/no-unused-vars
export interface MessageInterface {
  id: number
  text: string
  time: string
  from: UserInterface
}

export interface ChatInterface {
  id: number
  name: string
  avatar: AvatarInterface
  messages: MessageInterface[]
  users: UserInterface[]
  description: string
}
