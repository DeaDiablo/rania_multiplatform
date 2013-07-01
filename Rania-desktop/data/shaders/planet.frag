#ifdef GL_ES
    precision mediump float;
#endif

varying vec4 v_color;
varying vec2 v_texCoords;
uniform sampler2D u_texture;
uniform sampler2D u_texture2;
uniform vec2 v_speed;

void main() {
    vec4 planet = v_color * texture2D(u_texture, v_texCoords);
    vec4 cloud  = texture2D(u_texture2, v_texCoords + v_speed);
    planet.rgb = mix(planet.rgb, cloud.rgb, cloud.a);
    gl_FragColor = planet;
}