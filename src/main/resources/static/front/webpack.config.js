const path = require('path');

module.exports = {
	context: path.resolve(__dirname, './src'),
	entry: {
        join: ["@babel/polyfill", "./join.js"],

        term: ["@babel/polyfill", "./term.js"],
        login: ["@babel/polyfill", "./login.js"],
        expOfferRegit: ["@babel/polyfill", "./expOfferRegit.js"],
        expRequestRegit: ["@babel/polyfill", "./expRequestRegit.js"],
        main: ["@babel/polyfill", "./main.js"],
        expOfferList: ["@babel/polyfill", "./expOfferList.js"],
        expRequestList: ["@babel/polyfill", "./expRequestList.js"],
        expOffer: ["@babel/polyfill", "./expOffer.js"]
    },
	devtool: 'sourcemaps',
	cache: true,
	output: {
		path: __dirname,
		filename: './dist/[name].bundle.js'
	},
	mode: 'none',
	module: {
		rules: [{
			test: /\.jsx?$/,
			exclude: /(node_modules)/,
			use: {
				loader: 'babel-loader',
				options: {
					presets: [ '@babel/preset-env', '@babel/preset-react'],
					plugins: [
						'@babel/plugin-proposal-class-properties'
					]
				}
			}
		}, {
			test: /\.css$/,
			use: [ 
				'style-loader', 
				{
					loader:'css-loader',
					options: {
						modules: true
					}
				}]
		} ]
	}
};
