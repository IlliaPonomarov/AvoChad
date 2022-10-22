<template>
  <div>
    <q-item clickable @click="createNewChat()">
      <q-item-label>New chat</q-item-label>
      <q-dialog v-model="persistent" persistent transition-show="scale" transition-hide="scale">
        <q-card class="bg-teal text-white" style="width: 300px">
          <q-card-section>
            <div class="text-h6">Persistent</div>
          </q-card-section>

          <q-card-section class="q-pt-none">
            Click/Tap on the backdrop.
          </q-card-section>

          <q-card-actions align="right" class="bg-white text-teal">
            <q-btn flat label="OK" v-close-popup />
          </q-card-actions>
        </q-card>
      </q-dialog>
    </q-item>
  </div>
</template>

<script>
import { ref } from "vue"
import { useQuasar } from "quasar"

export default {
  name: "DialogNewChat",

  setup () {
    const $q = useQuasar()
    function createNewChat () {
      $q.dialog({
        title: 'Create Chat',
        form: {
          name: {
            type: 'text',
            label: 'Textbox',
            model: ''
          },
          pass: {
            type: 'password',
            label: 'Password',
            model: ''
          },
          age: {
            type: 'number',
            label: 'Numeric',
            model: 10,
            min: 1,
            max: 100
          },
          tags: {
            type: 'chips',
            label: 'Chips',
            model: ['Joe', 'John']
          },
          comments: {
            type: 'textarea',
            label: 'Textarea',
            model: ''
          }
        }
      }).onOk(() => {
        console.log('ok ok ok')
      })
    }
    return {
      createNewChat
    }
  }
}
</script>

<style scoped>

</style>
