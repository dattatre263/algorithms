package algospecialization.greedyandspanningtrees.datastructure;

public class Job {

  private Integer weight;
  private Integer length;
  private Integer difference;
  private Double ratio;

  public Integer getWeight() {
    return weight;
  }


  public Integer getLength() {
    return length;
  }

  public Integer getDifference() {
    return difference;
  }

  public Double getRatio() {
    return ratio;
  }

  public Job(int weight, int length){
    this.weight = weight;
    this.length = length;
    this.difference = this.weight - this.length;
    this.ratio = Double.valueOf(this.weight)/Double.valueOf(this.length);
  }

}
