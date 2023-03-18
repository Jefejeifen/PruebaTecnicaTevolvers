function fn(){

            var connectTimeout = karate.properties['connectTimeout'] || 15000
            karate.configure('connectTimeout', connectTimeout);
            karate.configure('readTimeout', connectTimeout)
            karate.configure('ssl',true);

            return {
                    api: {
                            bookingRestful: 'https://restful-booker.herokuapp.com',
                    }
            }
}