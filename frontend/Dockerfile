FROM node:16-alpine AS build
WORKDIR /app
ARG api_base_url
COPY package*.json ./
RUN npm install
COPY . .
RUN sed -i "s#baseUrl: 'http://localhost:8080',#baseUrl: '${api_base_url}',#" src/environments/environment.ts
RUN npm run build
EXPOSE 4200
CMD ["npm", "start", "--", "--host", "0.0.0.0"]