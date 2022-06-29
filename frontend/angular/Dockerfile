FROM node:16-alpine3.11 as build
RUN mkdir -p /app
WORKDIR /app
COPY package.json /app
RUN npm install
COPY . /app
RUN npm run build

FROM nginx:1.20.1
COPY --from=build /app/dist/angular /usr/share/nginx/html
EXPOSE 4200:80