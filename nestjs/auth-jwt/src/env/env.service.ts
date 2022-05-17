import { Injectable } from '@nestjs/common';
import * as dotenv from 'dotenv'
import * as fs from 'fs'

export interface EnvData {
    // application
    APP_ENV: string
    APP_DEBUG: boolean
  
    // database
    DATABASE_TYPE: 'mysql'
    DATABASE_HOST?: string
    DATABASE_NAME: string
    DATABASE_PORT?: number
    DATABASE_USER: string
    DATABASE_PASSWORD: string
  }

@Injectable()
export class EnvService {
    private vars: EnvData

  constructor () {
    const environment = process.env.NODE_ENV || 'dev'
    const data: any = dotenv.parse(fs.readFileSync(`${environment}.env`))

    data.APP_ENV = environment
    data.APP_DEBUG = data.APP_DEBUG === 'true' ? true : false
    data.DATABASE_PORT = parseInt(data.DATABASE_PORT)

    this.vars = data as EnvData
  }

  read (): EnvData {
    return this.vars
  }

  isDev (): boolean {
    return (this.vars.APP_ENV === 'dev')
  }

  isProd (): boolean {
    return (this.vars.APP_ENV === 'production')
  }
}
