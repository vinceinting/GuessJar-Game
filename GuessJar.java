public class GuessJar {
 
  public static void main(String[] args) {
    
    Jar jar = new Jar("", 0);
    Prompter prompter = new Prompter(jar);
    prompter.play();
    
  }
  
}