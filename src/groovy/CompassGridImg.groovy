
class CompassGridImg {
	
	public static void make_grid_img(config, ant, dimensions) {
		def images_dir = config.grass?.images_dir

		ant.exec(executable: "compass") {
			arg(line: "--grid-img ${dimensions} --images-dir ${images_dir} --force")
		}
		
	}	
	
}
