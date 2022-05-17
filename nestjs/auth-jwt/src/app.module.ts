import { Module } from '@nestjs/common';
import { TypeOrmModule } from '@nestjs/typeorm/dist/typeorm.module';
import { AppController } from './app.controller';
import { AppService } from './app.service';
import { User } from './user/user.entity';
import { UserModule } from './user/user.module';
import { AuthModule } from './auth/auth.module';
import { ConfigModule } from '@nestjs/config';
import { DatabaseModule } from './database/database.module';
import { DatabaseService } from './database/database.service';
import { EnvModule } from './env/env.module';

@Module({
//   imports: [TypeOrmModule.forRoot({
//     type: 'mysql',
//     host: 'localhost',
//     port: 3306,
//     username: 'root',
//     password: '',
//     database: 'nestjs-db',
//     entities: [User],
//     synchronize: true,
//   }), UserModule, AuthModule
// ],
  imports: [ConfigModule.forRoot({
    isGlobal: true,
    // envFilePath: '.dev.env',
  }), DatabaseModule, TypeOrmModule.forRootAsync({
    useClass: DatabaseService,
    inject: [DatabaseService]
  }), EnvModule],
  controllers: [AppController],
  providers: [AppService, DatabaseService],
})
export class AppModule {
}
