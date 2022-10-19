<template>
  <q-page>
    <div class="column q-pa-lg window-height window-width row justify-center items-center">
      <div class="row">
        <div class="text-white text-h5 justify-start items-start text-bold q-mb-lg">Sing In</div>
      </div>
      <div class="row">
        <q-card square class="shadow-24">
          <q-card-section class="q-pa-lg-lg">
            <q-form class="q-gutter-lg-md" @submit.prevent.stop="onSubmit()">
              <q-input
                       :rules="emailValidate"
                       filled
                       class="q-ma-lg-md"
                       v-model="loginFormState.email"
                       ref="emailRef"
                       standout type="email"
                       hint="Enter your email"
                       lazy-rules
                       label="Email">

                <template v-slot:prepend>
                  <q-icon name="email" />
                </template>
              </q-input>
              <br/>
              <q-input
                :rules="passwordValidate"
                class="q-ma-lg-md"
                v-model="loginFormState.password"
                ref="passwordRef"
                type="password"
                standout
                lazy-rules
                label="Password">

                <template v-slot:prepend>
                  <q-icon name="lock" />
                </template>
                <template v-slot:append>
                  <q-icon class="cursor-pointer" />
                </template>
              </q-input>
            </q-form>
          </q-card-section>

          <q-card-actions class="q-px-lg">
            <MyButton button-label="sing in"></MyButton>
            <MyButton class="q-ml-lg" button-label="sing up"></MyButton>
            <q-card class="text-center q-ml-lg-auto">
              <p class="text-grey-6">Forgot password?</p>
            </q-card>
          </q-card-actions>

        </q-card>
      </div>
    </div>
  </q-page>
</template>

<script lang="ts">
import MyButton from "../components/MyButton.vue"
import Validator from '../validation/Validator'
import { useQuasar } from "quasar"
import { reactive } from "vue"

export default {
  name: "LoginPage",
  components: { MyButton },

  setup () {
    const emailValidate = [(val: string) => Validator.emailValidation(val) || 'Enter email correctly']
    const passwordValidate = [(val: string) => Validator.passwordValidation(val) || 'Enter password correctly']

    type AccountFormState = {
      email: string;
      password: string;
    };

    const loginFormState: AccountFormState = reactive({
      email: '',
      password: ''
    })

    function onSubmit (): void {
      console.log('loginFormState', loginFormState)
    }

    return {
      loginFormState, onSubmit, emailValidate, passwordValidate
    }
  }
}
</script>

<style scoped>

</style>
