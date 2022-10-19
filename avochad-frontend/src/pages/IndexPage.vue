<template>
  <q-page class="q-pa-md">
<!--    <div class="row">-->
<!--      <div class="col-md-3 col-sm-6" style="background-color: blue; color: white">First column</div>-->
<!--      <div class="col-6" style="background-color: #21BA45; color: white">Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum</div>-->
<!--      <div class="col-3" style="background-color: red ; color: white">Third column</div>-->
<!--    </div>-->
      <div class="row">
        <div class="col-sm-9">
          <p>Level 1: .col-sm-9</p>
          <div class="row">
            <div class="col-8 col-sm-6">
              Level 2: .col-8 .col-sm-6
            </div>
            <div class="col-4 col-sm-6">
              Level 2: .col-4 .col-sm-6
            </div>
          </div>
        </div>
      </div>
    <q-chat-message :text="['hey, how are you?']"></q-chat-message>
  </q-page>
</template>

<script lang="ts">
import { Todo, Meta } from 'components/models'
import { defineComponent, ref } from 'vue'
import { Dark } from "quasar"

export default defineComponent({
  name: 'IndexPage',
  setup () {
    const todos = ref<Todo[]>([
      {
        id: 1,
        content: 'ct1'
      },
      {
        id: 2,
        content: 'ct2'
      },
      {
        id: 3,
        content: 'ct3'
      },
      {
        id: 4,
        content: 'ct4'
      },
      {
        id: 5,
        content: 'ct5'
      }
    ])
    const meta = ref<Meta>({
      totalCount: 1200
    })
    console.log(Dark.isActive)
    Dark.set(true)
    return { todos, meta }
  }
})
</script>
