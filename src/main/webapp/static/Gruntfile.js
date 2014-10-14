module.exports = function(grunt) {
  require('load-grunt-tasks')(grunt);

  grunt.initConfig({
    "bower-install-simple": {
        "install": {}
    },

    bower_concat: {
      all: {
        dest: 'js/all.js',
        cssDest: 'css/all.css'
      }
    },

    copy: {
      fonts: {
        expand: true,
        flatten: true,
        src: [ 'bower_components/bootstrap/fonts/*' ],
        dest: 'fonts'
      }
    },

    uglify: {
       bower: {
        options: {
          mangle: true,
          compress: true
        },
        files: {
          'js/all.min.js': 'js/all.js'
        }
      }
    },

    cssmin: {
      bower: {
        files: {
          'css/all.min.css': 'css/all.css'
        }
      }
    }
  });

  grunt.registerTask('default', [ 'bower-install-simple', 'copy', 'bower_concat', 'uglify', 'cssmin' ]);
};