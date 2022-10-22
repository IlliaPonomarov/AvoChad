<template>
  <q-page>
    <div class="column q-pa-lg window-height window-width row justify-center items-center">
      <div class="row">
        <div class="text-white text-h5 justify-start items-start text-bold q-mb-lg">Register</div>
      </div>
      <div class="row">
        <q-card square>
          <q-card-section class="q-pa-lg-lg">
            <q-form @submit="onSubmit" @reset="onReset" class="q-gutter-md">
              <q-input
                v-model="name"
                label="Name"
                filled
                lazy-rules
                :rules="[val => val.length > 0 || 'Please type your name']"
              />
              <q-input
                v-model="email"
                label="E-mail"
                filled
                lazy-rules
                :rules=emailRules
              />
              <q-input
                v-model="password"
                label="Password"
                type="password"
                filled
                lazy-rules
                :rules=passwordRules
              />
              <q-input
                v-model="password2"
                label="Confirm Password"
                type="password"
                filled
                lazy-rules
                :rules="[val => val.length > 0 || 'Please type your password', val => passMatch() || 'Passwords do not match']"
              />
              <div class="row justify-center">
                <q-btn label="Register" type="submit" color="primary" />
              </div>
            </q-form>
          </q-card-section>
        </q-card>
      </div>
    </div>
  </q-page>
</template>

<script lang="ts">
import { defineComponent, onMounted } from "vue"
import Validator from 'src/validation/Validator'

export default defineComponent({
  name: 'RegisterPage',
  setup () {
    return {
      passwordRules: [
        (val: string) => val.length > 0 || 'Please type your password',
        (val: string) => Validator.passwordValidation(val) || 'Use at least 8 characters, 1 number, 1 uppercase and 1 lowercase letter'
      ],
      emailRules: [
        (val: string) => val.length > 0 || 'Please type your email',
        (val: string) => Validator.emailValidation(val) || 'Please type a valid email'
      ]
    }
  },
  data () {
    return {
      name: '' as string,
      email: '' as string,
      password: '' as string,
      password2: '' as string
    }
  },
  methods: {
    onSubmit (): void {
      console.log('submit')
      window.location.href = '/#/'
    },
    onReset (): void {
      console.log('reset')
    },
    passMatch (): boolean {
      return this.password === this.password2
    }
  }
})
</script>
