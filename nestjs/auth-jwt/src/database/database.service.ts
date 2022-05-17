import { DynamicModule, Injectable } from '@nestjs/common';
import { ConfigService } from '@nestjs/config';
import { TypeOrmModuleOptions, TypeOrmOptionsFactory } from '@nestjs/typeorm/dist/interfaces/typeorm-options.interface';

@Injectable()
export class DatabaseService implements TypeOrmOptionsFactory {
    constructor(private configService: ConfigService) {}

    // createTypeOrmOptions(connectionName?: string): TypeOrmModuleOptions | Promise<TypeOrmModuleOptions> {
    createTypeOrmOptions(): TypeOrmModuleOptions {
        return {
            type: 'mysql',
            url: this.configService.get<string>('DATABASE_HOST'),
            port: this.configService.get<number>('DATABASE_PORT'),
            username: this.configService.get<string>('DATABASE_USER'),
            password: this.configService.get<string>('DATABASE_PASSWORD'),
            database: this.configService.get<string>('DATABASE_NAME'),
        };
    }
    
}
