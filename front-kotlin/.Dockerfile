# Use the official Node.js 14 image as a base image
FROM node:14

# Set the working directory
WORKDIR /app

# Copy package.json and package-lock.json files to the container
COPY package*.json ./

# Install dependencies
RUN npm install

# Copy the rest of the application code
COPY . .

# Expose the port that your Angular application runs on
EXPOSE 4200

# Command to run the application
CMD ["npm", "start"]
