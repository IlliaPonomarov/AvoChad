<template>
  <q-page>
    <div class="column q-pa-lg window-height window-width row justify-center items-center">
      <div class="row">
        <div class="text-white text-h5 justify-start items-start text-bold q-mb-lg">Sing In</div>
      </div>
      <div class="row">
        <q-card square class="shadow-24" style="width:400px;height:270px;">
          <q-card-section class="q-pa-lg-lg">
            <q-form class="q-gutter-lg-md" @submit.prevent.stop="onSubmit" @reset.prevent.stop="onReset()">
              <q-input
                       :rules="emailRules"
                       filled
                       class="q-ma-lg-md"
                       v-model="email"
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
                :rules="passwordRules"
                class="q-ma-lg-md"
                v-model="password"
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
import { ref } from "vue"
import {strict} from "assert";
export default {
  name: "LoginPage",
  components: { MyButton },
  setup () {
    const $q = useQuasar()

    const email = ref(null)
    const emailRef = ref(null)

    const passwordRef = ref(null)
    const password = ref(null)

    const accept = ref(false)

    return {
      email,
      emailRef,
      emailRules: [(val: string) => Validator.emailValidation(val) || "Please type email correctly"],

      password,
      passwordRef,
      passwordRules: [(val: string) => Validator.passwordValidation(val) || "Please type password correctly"],

      accept,

      onSubmit () {

        if(Validator.passwordValidation((password as string)) )
      },

      onReset () {
        email.value = null
        password.value = null
      }
    }
  }
}
</script>

<style scoped>

</style>
