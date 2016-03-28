module.exports = function(grunt) {

    // Project configuration.
    grunt.initConfig({
        pkg: grunt.file.readJSON('package.json'),
        //uglify ����
        uglify: {
            options: {
                banner: '/* <%= grunt.template.today("yyyy-mm-dd") %> / ' //������ ��ó�� �ٴ� banner ����
            },
            build: {
                src: 'public/build/result.js', //uglify�� ��� ����
                dest: 'public/build/result.min.js' //uglify ��� ���� ����
            }
        },
        //concat ����
        concat:{
            basic: {
                src: ['public/js/common/util.js', 'public/js/app.js', 'public/js/lib/.js', 'public/js/ctrl/.js'], //concat Ÿ�� ����(�տ������� ������� ��������.)
                    dest: 'public/build/result.js' //concat ��� ����
}
}
});

// Load the plugin that provides the "uglify", "concat" tasks.
grunt.loadNpmTasks('grunt-contrib-uglify');
grunt.loadNpmTasks('grunt-contrib-concat');

// Default task(s).
grunt.registerTask('default', ['concat', 'uglify']); //grunt ��ɾ�� ������ �۾�

};
