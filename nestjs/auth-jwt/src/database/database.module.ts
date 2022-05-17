import { DynamicModule, Global, Module } from '@nestjs/common';
import { ConfigModule } from '@nestjs/config';
import { TypeOrmModule } from '@nestjs/typeorm';
import { EnvModule } from 'src/env/env.module';
import { EnvService } from 'src/env/env.service';
import { DatabaseService } from './database.service';

function DatabaseOrmModule (): DynamicModule {
  const config = new EnvService().read()

  return TypeOrmModule.forRoot({
    type: config.DATABASE_TYPE,
    host: config.DATABASE_HOST,
    port: config.DATABASE_PORT,
    username: config.DATABASE_USER,
    password: config.DATABASE_PASSWORD,
    database: config.DATABASE_NAME,

    synchronize: false
  })
}

@Global()
@Module({
  imports: [EnvModule],
  providers: [DatabaseService],
  exports: [DatabaseService]
})
export class DatabaseModule {}
