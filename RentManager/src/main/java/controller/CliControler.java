package controller;
import java.io.IOException;
import java.sql.Date;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import com.ensta.rentmanager.exception.DaoException;
import com.ensta.rentmanager.exception.ServiceException;
import com.ensta.rentmanager.model.Client;
import com.ensta.rentmanager.model.Reservation;
import com.ensta.rentmanager.model.Vehicle;
import com.ensta.rentmanager.service.ClientService;
import com.ensta.rentmanager.service.ReservationService;
import com.ensta.rentmanager.service.VehicleService;

public class CliControler {
	private ClientService clientservice = ClientService.getInstance();
	private VehicleService vehicleservice = VehicleService.getInstance();
	private ReservationService reservationservice = ReservationService.getInstance();
	
	public static void main(String[] args) throws IOException, InterruptedException, DaoException {
		CliControler cli = new CliControler();
		Scanner sc=new Scanner(System.in);
		boolean continuer=true;
		int choice=0;
		while(continuer) {
			cli.clientservice.printBetween();
			while(choice !=1 && choice!=2 && choice!=3 && choice!=4) {
				System.out.print("Votre choix : ");
				choice= sc.nextInt();
				sc.nextLine();
			}
			if(choice==1) {
					cli.clientservice.printChoix();
					int choix=0;
					if(choix>=5 || choix<1) {
						System.out.print("Votre choix : ");
						choix = sc.nextInt();
						sc.nextLine();
					}
					switch(choix) {
						case 1:
							System.out.print("Nom du client : ");
							String nom = sc.nextLine();
							System.out.print("Prenom du client : ");
							String prenom=sc.nextLine();
							System.out.print("Email du client : ");
							String email=sc.nextLine();
							System.out.print("Date de naissance du client : ");
							String naissance=sc.nextLine();
							Client client = new Client(nom,prenom,email,naissance);
							Thread.sleep(700);
							try {
								cli.clientservice.create(client);
								System.out.println("le client "+nom.toUpperCase()+" "+prenom+" a été ajouté !");
							}catch(ServiceException e) {
								System.out.println("Erreur lors de l'enregistrement du client : "+e.getMessage());
							}
				
							System.out.println("Appuyer sur une touche pour retourner au menu principal...");
							char car = (char) System.in.read();
							break;
							
						case 2:
							System.out.print("ID du client à supprimer : ");
							int id=sc.nextInt();
							sc.nextLine();
							try {
								long res =cli.clientservice.deleteClient(id);
								while(res ==0) {
									System.out.println("le client n°"+id+" n'existe pas! ");
									System.out.print("ID du client à supprimer : ");
									id=sc.nextInt();
									sc.nextLine();
									res =cli.clientservice.deleteClient(id);
								}
							}catch(ServiceException e) {
								System.out.println("Erreur lors du Select ALL : "+e.getMessage());
							}
							System.out.println("le client n°"+id+" a été supprimé !");
							Thread.sleep(700);
							System.out.println("Entrée pour retourner au menu principal...");
							char car1 = (char) System.in.read();
							break;
							
						case 3:
							try {
								List<Client> list=cli.clientservice.findAll();
								for(Client c : list) {
									System.out.println(c);
								}
							}catch(ServiceException e) {
								System.out.println("Erreur lors du Select ALL : "+e.getMessage());
							}
							System.out.println("Entrée pour retourner au menu principal...");
							char car2 = (char) System.in.read();
							break;
						case 4:
							System.out.print("ID du client à modifier : ");
							int id_modif=sc.nextInt();
							sc.nextLine();
							try {
								Client clien =cli.clientservice.findById(id_modif);
								while(clien.getId()==0) {
									System.out.print("ID du client à modifier : ");
									id_modif=sc.nextInt();
									sc.nextLine();
									clien =cli.clientservice.findById(id_modif);
								}
								
							} catch (ServiceException e) {
								System.out.println("le client n'existe pas!");
							}
							cli.clientservice.printUpdate(id_modif);
							String out=sc.nextLine();
							String[] arrayout=out.split("");
							if(Arrays.asList(arrayout).contains("1")) {
								System.out.print("nouveau nom : ");
								String name=sc.nextLine();
								try {
									cli.clientservice.updateName(name, id_modif);
								} catch (ServiceException e) {
									e.printStackTrace();
								}
							}
							if(Arrays.asList(arrayout).contains("2")) {
								System.out.print("nouveau prénom : ");
								String lastname=sc.nextLine();
								try {
									cli.clientservice.updatePrenom( lastname, id_modif);
								} catch (ServiceException e) {
									e.printStackTrace();
								}
							}
							if(Arrays.asList(arrayout).contains("3")) {
								System.out.print("nouveau email : ");
								String mail=sc.nextLine();
								try {
									cli.clientservice.updateEmail( mail, id_modif);
								} catch (ServiceException e) {
									e.printStackTrace();
								}
							}
							if(Arrays.asList(arrayout).contains("4")) {
								System.out.print("nouvelle date de naissance : ");
								String birthdate=sc.nextLine();
								try {
									cli.clientservice.updateBirth( birthdate, id_modif);
								} catch (ServiceException e) {
									e.printStackTrace();
								}
							}
							if(Arrays.asList(arrayout).contains("5")) {
								break;
							}
							System.out.println("Le client n°"+id_modif+" a été updaté !");
							System.out.println("Entrée pour retourner au menu principal...");
							char car3 = (char) System.in.read();
							break;
						case 5:
							System.out.println("Au revoir");
							choice=0;
					}
			}else if(choice==2) {
					cli.vehicleservice.printChoix();
					int choix=0;
					if(choix>=5 || choix<1) {
						System.out.print("Votre choix : ");
						choix = sc.nextInt();
						sc.nextLine();
					}
					switch(choix) {
						case 1:
							System.out.print("modèle du véhicule : ");
							String modele = sc.nextLine();
							System.out.print("constructeur du véhicule : ");
							String constructeur=sc.nextLine();
							System.out.print("nombre de places du véhicule : ");
							int nb_place=sc.nextInt();
							sc.nextLine();
							Vehicle vehicle = new Vehicle(modele,constructeur,nb_place);
							Thread.sleep(700);
							try {
								cli.vehicleservice.create(vehicle);
									System.out.println("le vehicle "+modele.toUpperCase()+" de "+constructeur+" a été ajouté !");
								}catch(ServiceException e) {
									System.out.println("Erreur lors du Select ALL : "+e.getMessage());
								}
							System.out.println("Appuyer sur une touche pour retourner au menu principal...");
							char car = (char) System.in.read();
							break;
							
						case 2:
							System.out.print("ID du véhicule à supprimer : ");
							int id=sc.nextInt();
							sc.nextLine();
							try {
								long res =cli.vehicleservice.delete(id);
								while(res ==0) {
									System.out.println("le véhicule n°"+id+" n'existe pas! ");
									System.out.print("ID du véhicule à supprimer : ");
									id=sc.nextInt();
									sc.nextLine();
									res =cli.vehicleservice.delete(id);
								}
							}catch(ServiceException e) {
								System.out.println("Erreur lors du Select ALL : "+e.getMessage());
							}
							System.out.println("le véhicule n°"+id+" a été supprimé !");
							Thread.sleep(700);
							System.out.println("Entrée pour retourner au menu principal...");
							char car1 = (char) System.in.read();
							break;
							
						case 3:
							try {
								List<Vehicle> list=cli.vehicleservice.findAll();
								for(Vehicle v : list) {
									System.out.println(v);
								}
							}catch(ServiceException e) {
								System.out.println("Erreur lors du Select ALL : "+e.getMessage());
							}
							System.out.println("Entrée pour retourner au menu principal...");
							char car2 = (char) System.in.read();
							break;
						case 4:
							System.out.print("ID du véhicule à modifier : ");
							int id_modif=sc.nextInt();
							sc.nextLine();
							try {
								Vehicle vhcl =cli.vehicleservice.findById(id_modif);
								while(vhcl.getId()==0) {
									System.out.print("ID du véhicule à modifier : ");
									id_modif=sc.nextInt();
									sc.nextLine();
									vhcl =cli.vehicleservice.findById(id_modif);
								}
								
							} catch (ServiceException e) {
								System.out.println("le véhicule n'existe pas!");
							}
							cli.vehicleservice.printUpdate(id_modif);
							String out=sc.nextLine();
							String[] arrayout=out.split("");
							if(Arrays.asList(arrayout).contains("2")) {
								System.out.print("nouveau modèle : ");
								String new_modele=sc.nextLine();
								try {
									cli.vehicleservice.updateModele(new_modele, id_modif);
								} catch (ServiceException e) {
									e.printStackTrace();
								}
							}
							if(Arrays.asList(arrayout).contains("1")) {
								System.out.print("nouveau constructeur : ");
								String new_constructeur=sc.nextLine();
								try {
									cli.vehicleservice.updateConstructeur( new_constructeur, id_modif);
								} catch (ServiceException e) {
									e.printStackTrace();
								}
							}
							if(Arrays.asList(arrayout).contains("3")) {
								System.out.print("nouveau nb_place : ");
								int nb_places=sc.nextInt();
								sc.nextLine();
								try {
									cli.vehicleservice.updateNb_places( nb_places, id_modif);
								} catch (ServiceException e) {
									e.printStackTrace();
								}
							}
							if(Arrays.asList(arrayout).contains("4")) {
								break;
							}
							System.out.println("Le véhicule n°"+id_modif+" a été updaté !");
							System.out.println("Entrée pour retourner au menu principal...");
							char car3 = (char) System.in.read();
							break;
						case 5:
							System.out.println("Au revoir");
							choice=0;
					}	
				
			}else if(choice==3) {
				cli.reservationservice.printReservationChoices();
				int choix=0;
				if(choix>=5 || choix<1) {
					System.out.print("Votre choix : ");
					choix = sc.nextInt();
					sc.nextLine();
				}
				switch(choix) {
				case 1:
					System.out.print("id_client : ");
					int id_client = sc.nextInt();
					sc.nextLine();
					System.out.print("id_véhicule : ");
					int id_vehicle=sc.nextInt();
					sc.nextLine();
					System.out.print("date de début : ");
					String debut=sc.nextLine();
					Date date_debut = Date.valueOf(debut);
					System.out.print("date de fin : ");
					String fin=sc.nextLine();
					Date date_fin = Date.valueOf(fin);
					Reservation reservation = new Reservation(id_client,id_vehicle,date_debut,date_fin);
					Thread.sleep(700);
					try {
						cli.reservationservice.create(reservation);
						System.out.println("une réservation pour le client "+id_client+" a été ajouté (From :"+debut+" to : "+fin+" )");
					}catch(ServiceException e) {
						System.out.println("Erreur lors de l'enregistrement du client : "+e.getMessage());
					}
		
					System.out.println("Appuyer sur une touche pour retourner au menu principal...");
					char car = (char) System.in.read();
					break;
				case 2:
					System.out.print("ID de la réservation à supprimer : ");
					int id=sc.nextInt();
					sc.nextLine();
					try {
						long res =cli.reservationservice.deleteReservation(id);
						while(res ==0) {
							System.out.println("la réservation n°"+id+" n'existe pas! ");
							System.out.print("ID de la réservation à supprimer : ");
							id=sc.nextInt();
							sc.nextLine();
							res =cli.reservationservice.deleteReservation(id);
						}
					}catch(ServiceException e) {
						System.out.println("Erreur lors du Select ALL : "+e.getMessage());
					}
					System.out.println("a réservation n°"+id+" a été supprimé !");
					Thread.sleep(700);
					System.out.println("Entrée pour retourner au menu principal...");
					char car1 = (char) System.in.read();
					break;
				case 3:
					System.out.print("ID du client : ");
					int clientid=sc.nextInt();
					sc.nextLine();
					try {
						List<Reservation> list=cli.reservationservice.findClientReservations(clientid);
						if(list.size()==0) {
							System.out.println("Pas de réservation ou le client n'existe pas !!");
						}else {
							for(Reservation c : list) {
								System.out.println(c);
							}
						}
					}catch(ServiceException e) {
						System.out.println("Erreur lors du Select ALL : "+e.getMessage());
					}
					System.out.println("Entrée pour retourner au menu principal...");
					char car2 = (char) System.in.read();
					break;
				case 4:
					System.out.print("ID du véhicule : ");
					int vehicleid=sc.nextInt();
					sc.nextLine();
					try {
						List<Reservation> list=cli.reservationservice.findVehicleReservations(vehicleid);
						if(list.size()==0) {
							System.out.println("Pas de réservation ou le véhicule n'existe pas !!");
						}else {
							for(Reservation c : list) {
								System.out.println(c);
							}
						}
					}catch(ServiceException e) {
						System.out.println("Erreur lors du Select ALL : "+e.getMessage());
					}
					System.out.println("Entrée pour retourner au menu principal...");
					char car3 = (char) System.in.read();
					break;
				case 5:
					try {
						List<Reservation> list=cli.reservationservice.FindAll();
						if(list.size()==0) {
							System.out.println("Pas de réservation !!");
						}else {
							for(Reservation c : list) {
								System.out.println(c);
							}
						}
					}catch(ServiceException e) {
						System.out.println("Erreur lors du Select ALL : "+e.getMessage());
					}
					System.out.println("Entrée pour retourner au menu principal...");
					char car4 = (char) System.in.read();
					break;
				case 6:
					System.out.println("Au revoir");
					choice=0;
				}
			}
			else if(choice==4){
				System.out.println("Au revoir");
				System.exit(1);
			}
			
		}
		sc.close();

	}

}
